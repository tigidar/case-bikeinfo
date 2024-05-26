import globals from "globals";
import pluginJs from "@eslint/js";


export default [
  {languageOptions: { globals: globals.browser }},
  { parserOptions: {
    sourceType: "module",
    ecmaVersion: 2020
  }},
  pluginJs.configs.recommended,
];

