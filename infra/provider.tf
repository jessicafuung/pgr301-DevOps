terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "4.33.0"
    }
  }
  
  backend "s3" {
    bucket = "analytics-1044"
    key    = "1044/shopifly.state"
    region = "eu-west-1"
  }
}
