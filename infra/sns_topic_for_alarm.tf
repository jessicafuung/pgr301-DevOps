resource "aws_sns_topic" "alarms" {
  name = "alarm-topic-${var.candidate_id}"
}

resource "aws_sns_topic_subscription" "user_updates_sqs_target" {
  topic_arn = aws_sns_topic.alarms.arn
  protocol  = "email"
  endpoint  = var.candidate_email
}

resource "aws_cloudwatch_metric_alarm" "over5" {
  alarm_name                = "carts-over-5"
  namespace                 = var.candidate_id
  metric_name               = "carts_count.value"

  comparison_operator       = "GreaterThanThreshold"
  threshold                 = "5"
  evaluation_periods        = "3"
  period                    = "20"

  statistic                 = "Maximum"

  alarm_description         = "Alarm for carts count exceeding 5 for 3 consecutive periods of 5 minutes"
  insufficient_data_actions = []
  alarm_actions       = [aws_sns_topic.alarms.arn]
}