FROM node:23.6.0-alpine3.21

WORKDIR /app

COPY package.json . 

RUN npm install 

COPY . .

EXPOSE 117 

CMD [ "node", "index.js" ]