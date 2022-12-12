resource "aws_sns_topic" "alarms" {
  name = "alarm-topic-${var.candidate_id}"
  namespace                 = "1044"
  metric_name               = "carts_count.value"

  comparison_operator       = "GreaterThanThreshold"
  threshold                 = "5"
  evaluation_periods        = "3"
  period                    = "300"

  statistic                 = "Maximum"

  alarm_description         = "This alarm goes carts over 5"
  insufficient_data_actions = []
  alarm_actions       = [aws_sns_topic.user_updates.arn]
}

resource "aws_sns_topic_subscription" "user_updates_sqs_target" {
  topic_arn = aws_sns_topic.alarms.arn
  protocol  = "email"
  endpoint  = "chfu003@student.kristiania.no"
}

resource "aws_sns_topic" "user_updates" {
  name = var.candidate_id
}
