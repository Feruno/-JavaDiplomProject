FROM node:lts-alpine3.12
WORKDIR gate-simulator
COPY /gate-simulator /gate-simulator
RUN npm install
CMD ["npm", "start"]
EXPOSE 9999