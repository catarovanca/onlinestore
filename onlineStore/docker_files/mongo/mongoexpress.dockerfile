FROM mongo-express:latest
ENV ME_CONFIG_BASICAUTH_USERNAME root
ENV ME_CONFIG_BASICAUTH_PASSWORD mongoExpressRoot
EXPOSE 8081