terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "4.33.0"
    }
  }
  backend "s3" {
    bucket = "pgr301-2022-terraform-state"
    key    = "1044/shopifly.state"
    region = "eu-north-1"
  }
}
