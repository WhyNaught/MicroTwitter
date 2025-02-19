FROM node:23.6.0-alpine3.21 

WORKDIR /app

COPY package.json .

RUN npm install --verbose

COPY . .

EXPOSE 5003

CMD ["node", "index.js"]