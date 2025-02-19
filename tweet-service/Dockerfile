FROM python:3.11-bullseye

RUN apt-get update && apt-get install -y --no-install-recommends \
    build-essential \
  && pip install --no-cache-dir --upgrade pip \
  && apt-get clean \
  && rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY ./requirements.txt /app/
RUN pip3 install -r /app/requirements.txt
COPY . /app/

ENV FLASK_APP=app.py 
ENV FLASK_ENV=development

EXPOSE 5001 

CMD [ "python3", "app.py"]