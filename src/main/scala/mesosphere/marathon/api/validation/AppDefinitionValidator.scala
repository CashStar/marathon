package mesosphere.marathon.api.validation

import javax.validation.{ ConstraintValidatorContext, ConstraintValidator }

import mesosphere.marathon.state.{ AppDefinition, Container }

class AppDefinitionValidator extends ConstraintValidator[ValidAppDefinition, AppDefinition] {
  override def initialize(constraintAnnotation: ValidAppDefinition): Unit = {}

  override def isValid(
    value: AppDefinition,
    context: ConstraintValidatorContext): Boolean = {
    val cmd = value.cmd.nonEmpty
    val args = value.args.nonEmpty
    val container = value.container.exists(_ != Container.Empty)
    (cmd ^ args) || (!(cmd || args) && container)
  }

}
