FROM nixos/nix

RUN nix-channel --update

COPY . /app

WORKDIR /app

# Build mill with nix-build
RUN nix-build -A mill '<nixpkgs>' -o /mill-link
RUN /mill-link/bin/mill app.assembly
RUN mkdir ./bin && cp ./out/app/assembly.dest/out.jar ./bin/

