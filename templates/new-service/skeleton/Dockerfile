FROM node:22-alpine AS deps
WORKDIR /app
RUN npm install -g npm@latest
COPY package*.json ./
RUN npm install --omit=dev

FROM node:22-alpine
# Atualiza os pacotes do SO no build.
RUN apk upgrade --no-cache
RUN npm install -g npm@latest
WORKDIR /app
COPY --from=deps /app/node_modules ./node_modules
COPY src ./src
COPY package.json ./

RUN addgroup -S app && adduser -S app -G app
USER app

EXPOSE 3000
CMD ["node", "--require", "@opentelemetry/auto-instrumentations-node/register", "src/index.js"]
