require("ggplot2")

d <- read.table("data/psql-mongo-persistence-analysis-02-16.tsv", sep = "\t", dec = ",")

colnames(d) <- c("database", "iterations", "operation", "images", "usertime", "cputime", "totaltime")

d <- d[-301,]

d$operation <- as.character(d$operation)

d$operation <- replace(d$operation, d$operation == "persistence", "Inclusão")
d$operation <- replace(d$operation, d$operation == "retrieve", "Recuperação")

d$operation <- as.factor(d$operation)

d1 <- aggregate(d$totaltime, by=list(d$database, d$images, d$operation), FUN = mean, na.rm = TRUE)
colnames(d1) <- c("database", "images", "operation", "totaltime.mean")

d2 <- aggregate(d$totaltime, by=list(d$database, d$images, d$operation), FUN = sd, na.rm = TRUE)
colnames(d2) <- c("database", "images", "operation", "totaltime.sd")

d <- merge(d1, d2)

d$images <- factor(d$images)

limits <- aes(ymax = d$totaltime.mean + d$totaltime.sd, ymin = d$totaltime.mean - d$totaltime.sd)

gplot <- ggplot(d) + geom_bar(aes(x = images, y = totaltime.mean), stat = "identity", position="dodge") + 
  #geom_errorbar(limits, position = position_dodge(width=0.9), width = 0.25) +
  facet_grid(database ~ operation) + 
  xlab("Número de Imagens por Transação") + ylab("Tempo de Execução Médio (ms)") + 
  theme(text = element_text(size=10))

ggsave("images/bar.png", gplot, units = "cm", width = 10, height = 8)