FROM nixos/nix

RUN nix-channel --update

RUN nix-build -A mill '<nixpkgs>'

COPY . /app

WORKDIR /app

CMD ["mill", "app/app.run"]

