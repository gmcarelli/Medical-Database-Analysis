require("ggplot2")

d <- read.table("data/psql-mongo-persistence-analysis-02-16.tsv", sep = "\t", dec = ",")

colnames(d) <- c("database", "iterations", "operation", "images", "usertime", "cputime", "totaltime")

d <- d[-301,]

d$operation <- as.character(d$operation)

d$operation <- replace(d$operation, d$operation == "persistence", "Inclusão")
d$operation <- replace(d$operation, d$operation == "retrieve", "Recuperação")

d$operation <- as.factor(d$operation)

gplot <- ggplot(d) + geom_histogram(aes(totaltime)) + facet_grid(database ~ operation) + 
  xlab("Tempo de Execução (ms)") + ylab("Frequência") +
  theme(text = element_text(size=10))

ggsave(plot = gplot, filename = "images/histogram.png", units = "cm", height = 8, width = 10) 
