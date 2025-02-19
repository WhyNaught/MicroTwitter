FROM node:23.6.0-alpine3.21 

WORKDIR /app

COPY package.json .

RUN npm cache clean --force

RUN npm install

COPY . .

EXPOSE 5002

CMD ["node", "index.js"]