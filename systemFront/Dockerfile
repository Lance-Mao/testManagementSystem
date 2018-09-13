
FROM node:8.11.1-slim

WORKDIR /app

COPY . ${WORKDIR}

RUN  npm install \
    && npm run build \
    && npm install -g serve

WORKDIR /app/dist

EXPOSE 8001

ENTRYPOINT [ "serve", "-s" ]
