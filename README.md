# Dekanat -застосунок для роботи


#Запуск
git clone https://github.com/DarkPoul/dekanatSpringVaadin.git
cd dekanatSpringVaadin
docker build -t dekanat .
docker run -d -p 80:8080 --name dekanat-container dekanat
