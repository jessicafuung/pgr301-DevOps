terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "4.33.0"
    }
  }
  backend "s3" {
    bucket = "analytics-${var.candidate_id}"
    key    = "1044/terraform-in-pipeline.state"
    region = "eu-west-1"
  }
}
