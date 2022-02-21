FROM node:17-alpine3.15 as build
WORKDIR /app
RUN apk add --no-cache curl git && cd /tmp && \
    curl -#L https://github.com/tj/node-prune/releases/download/v1.0.1/node-prune_1.0.1_linux_amd64.tar.gz | tar -xvzf- && \
    mv -v node-prune /usr/local/bin && rm -rvf * && \
    echo "yarn cache clean && node-prune" > /usr/local/bin/node-clean && chmod +x /usr/local/bin/node-clean
COPY ./package.json ./
RUN yarn --frozen-lockfile --non-interactive
COPY ./ ./
RUN yarn build
ENV NODE_ENV=production
RUN yarn --frozen-lockfile --non-interactive --production && node-clean

FROM node:17-alpine3.15
WORKDIR /app
RUN apk --no-cache add curl
ENV NODE_ENV=production NUXT_HOST=0.0.0.0
COPY package.json ./
COPY nuxt.config.js ./
COPY --from=build ./app/node_modules ./node_modules/
COPY --from=build ./app/.nuxt ./.nuxt/
COPY --from=build ./app/static ./static/
ARG USERNAME=nuxt
ENV UID=12345
ENV GID=23456
RUN addgroup -S $USERNAME \
    && adduser \
    --disabled-password \
    --gecos "" \
    --home "$(pwd)" \
    --ingroup "$USERNAME" \
    --no-create-home \
    --uid "$UID" \
    "$USERNAME" \
    && chown -R $USERNAME:$USERNAME /app
USER $USERNAME
EXPOSE 3000
HEALTHCHECK CMD curl --fail http://localhost:3000/ || exit 1
CMD ["yarn", "start"]
