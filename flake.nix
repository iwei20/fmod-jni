{
  description = "Gradle/Java Development";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixpkgs-unstable";
    ivanpkgs.url = "github:iwei20/nixpkgs/fix-jextract";
  };

  outputs = { self, nixpkgs, ivanpkgs }: 
    let
      supportedSystems = [ "x86_64-linux" "aarch64-linux" "x86_64-darwin" "aarch64-darwin" ];
      forEachSupportedSystem = f: nixpkgs.lib.genAttrs supportedSystems (system: f {
        pkgs = import nixpkgs {
          inherit system;
        };
        ivanpkgs = import ivanpkgs {
          inherit system;
        };
      });
    in
    {
      devShells = forEachSupportedSystem ({ pkgs, ivanpkgs }: {
        default = pkgs.mkShell {
          packages = with pkgs; [
            gradle
            jdk23
            ivanpkgs.jextract
          ];

          env = {
            JAVA_HOME = "${pkgs.jdk23}/lib/openjdk";
          };
        };
      });
    };
}
