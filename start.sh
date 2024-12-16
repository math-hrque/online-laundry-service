#!/bin/bash
set -e

build_images() {
  echo "🔨🚧 Starting the Docker images build process..."

  echo "🚀💻 Building Server image..."
  docker build -t server:latest ./server
  echo "✅📦 Server image built successfully!"

  echo "🎨🖌️ Building the Client image..."
  docker build -t client:latest ./client
  echo "✅🌟 Client image built successfully!"

  echo "🎉🚀 All images have been built successfully!"
}

start_containers() {
  echo "🔧⚙️ Starting services with Docker Compose..."
  docker-compose up -d
  echo "✅🎯 All services are up and running! Check logs using 'docker-compose logs'."
}

build_images
start_containers
