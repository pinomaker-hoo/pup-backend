docker build --no-cache --rm -f Dockerfile -t pinomaker/pup-backend:latest . --platform linux/amd64

docker push pinomaker/pup-backend:latest