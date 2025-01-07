FROM python:3.11-bullseye

RUN apt-get update && apt-get install -y --no-install-recommends \
    build-essential \
  && pip install --no-cache-dir --upgrade pip \
  && apt-get clean \
  && rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY requirements.txt requirements.txt
RUN pip3 install -r requirements.txt

COPY . .

ENV FLASK_APP=app.py 
ENV FLASK_ENV=development

CMD [ "python3", "-m", "flask", "run", "--host=0.0.0.0"]

# docker run -d -p 5000:5000 --name user-service user-service
