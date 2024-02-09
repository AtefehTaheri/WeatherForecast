package ir.atefehtaheri.weatherforecasts.domain.usecase

data class PermissionUseCase(
    val checkGps: CheckGps,
    val checkPermission: CheckPermission,
    val enableGps: EnableGps

)
