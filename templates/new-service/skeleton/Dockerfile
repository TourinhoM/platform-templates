FROM node:20-alpine AS deps
WORKDIR /app
RUN npm install -g npm@latest
COPY package*.json ./
RUN npm install --omit=dev

FROM node:20-alpine
# Pacotes do SO atualizados no build — a base image fica pra trás dos fixes
# do Alpine e o gate Trivy (HIGH/CRITICAL com fix disponível) bloqueia o push.
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
