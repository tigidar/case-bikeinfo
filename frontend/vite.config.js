import { defineConfig } from "vite";
import scalaJSPlugin from "@scala-js/vite-plugin-scalajs";
import path from 'path'

export default defineConfig({
  root: 'src/main/web',
  resolve : {
    alias: {
      '@my-modules/': path.resolve(__dirname, './src/main/web/'),
    },
  },
    // Alias to treat your custom JS as modules
  build: {
    outDir: '../../dist',
    emptyOutDir: true
  },
  plugins: [scalaJSPlugin()],
});

