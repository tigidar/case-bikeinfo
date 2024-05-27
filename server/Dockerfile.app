FROM nixos/nix

RUN nix-channel --update
RUN nix-build -A jdk22_headless '<nixpkgs>' -o /jdk-link

CMD mkdir -p /app/resources/bikeinfo/assets/
COPY ./app/resources/bikeinfo/assets/* /app/resources/bikeinfo/assets/

COPY ./dist/out.jar /usr/app/
WORKDIR /usr/app
CMD ["/jdk-link/bin/java", "-jar", "out.jar"]

