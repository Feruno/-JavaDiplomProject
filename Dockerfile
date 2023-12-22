FROM node:14.21.3-bullseye
WORKDIR /opt/app
COPY /gate-simulator ../opt/app
RUN npm install
CMD ["npm", "start"]
EXPOSE 9999