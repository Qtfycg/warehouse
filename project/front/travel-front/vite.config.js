

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'


export default defineConfig({
  plugins: [
    vue(),
  ],
  resolve: {
    alias: {
      
    },
    server: {
        host: '127.0.0.1',
        port: 8080,
        https: false
  }
})
