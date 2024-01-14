# Supprime les fichiers finissant par .class dans le répertoire courant, ./views, ./models et ./controllers

# Variables
SRC = .
MODELS = ./models
VIEWS = ./views

# Règles
all: clean compile

clean:
	@echo "Suppression des fichiers .class dans $(SRC)"
	@find $(SRC) -name "*.class" -type f -delete
	@echo "Suppression des fichiers .class dans $(MODELS)"
	@find $(MODELS) -name "*.class" -type f -delete
	@echo "Suppression des fichiers .class dans $(VIEWS)"
	@find $(VIEWS) -name "*.class" -type f -delete

compile:
	@echo "Compilation des fichiers .java dans $(SRC)"
	@javac $(SRC)/*.java
	@echo "Compilation des fichiers .java dans $(MODELS)"
	@javac $(MODELS)/*.java
	@echo "Compilation des fichiers .java dans $(VIEWS)"
	@javac $(VIEWS)/*.java

run:
	# clean and compile then run
	@make clean
	@make compile
	@echo "Lancement de l'application"
	@java MasterMindApp
