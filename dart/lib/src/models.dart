/// Response models for the Ovulation Calculator API.

/// API Response wrapper.
class OvulationcalculatorResponse {
  final String status;
  final dynamic error;
  final OvulationcalculatorData? data;

  /// Set only when the current plan locks one or more fields; null otherwise.
  final PremiumInfo? premium;

  OvulationcalculatorResponse({
    required this.status,
    this.error,
    this.data,
    this.premium,
  });

  factory OvulationcalculatorResponse.fromJson(Map<String, dynamic> json) => OvulationcalculatorResponse(
    status: json['status'] as String? ?? '',
    error: json['error'],
    data: json['data'] != null ? OvulationcalculatorData.fromJson(json['data']) : null,
    premium: json['premium'] != null ? PremiumInfo.fromJson(json['premium']) : null,
  );

  Map<String, dynamic> toJson() => {
    'status': status,
    if (error != null) 'error': error,
    if (data != null) 'data': data,
    if (premium != null) 'premium': premium!.toJson(),
  };
}

/// Describes fields the current plan does not unlock. Locked fields arrive as null
/// in [data], and [lockedFields] names them (nested ones use dot paths).
class PremiumInfo {
  final String? message;
  final String? upgradeUrl;
  final List<String>? lockedFields;

  PremiumInfo({this.message, this.upgradeUrl, this.lockedFields});

  factory PremiumInfo.fromJson(Map<String, dynamic> json) => PremiumInfo(
    message: json['message'] as String?,
    upgradeUrl: json['upgrade_url'] as String?,
    lockedFields: (json['locked_fields'] as List?)?.map((e) => e.toString()).toList(),
  );

  Map<String, dynamic> toJson() => {
    if (message != null) 'message': message,
    if (upgradeUrl != null) 'upgrade_url': upgradeUrl,
    if (lockedFields != null) 'locked_fields': lockedFields,
  };
}

/// Response data for the Ovulation Calculator API.

class OvulationcalculatorData {
  String? lastPeriod;
  num? cycleLength;
  OvulationcalculatorDataOvulation? ovulation;
  OvulationcalculatorDataFertileWindow? fertileWindow;
  OvulationcalculatorDataMostFertileDays? mostFertileDays;
  List<OvulationcalculatorDataFertileDaysItem>? fertileDays;
  OvulationcalculatorDataNextPeriod? nextPeriod;
  OvulationcalculatorDataCyclePhases? cyclePhases;
  OvulationcalculatorDataCurrentStatus? currentStatus;
  String? disclaimer;

  OvulationcalculatorData({
    this.lastPeriod,
    this.cycleLength,
    this.ovulation,
    this.fertileWindow,
    this.mostFertileDays,
    this.fertileDays,
    this.nextPeriod,
    this.cyclePhases,
    this.currentStatus,
    this.disclaimer,
  });

  factory OvulationcalculatorData.fromJson(Map<String, dynamic> json) => OvulationcalculatorData(
      lastPeriod: json['last_period'],
      cycleLength: json['cycle_length'],
      ovulation: json['ovulation'] != null ? OvulationcalculatorDataOvulation.fromJson(json['ovulation']) : null,
      fertileWindow: json['fertile_window'] != null ? OvulationcalculatorDataFertileWindow.fromJson(json['fertile_window']) : null,
      mostFertileDays: json['most_fertile_days'] != null ? OvulationcalculatorDataMostFertileDays.fromJson(json['most_fertile_days']) : null,
      fertileDays: (json['fertile_days'] as List?)?.map((e) => OvulationcalculatorDataFertileDaysItem.fromJson(e)).toList(),
      nextPeriod: json['next_period'] != null ? OvulationcalculatorDataNextPeriod.fromJson(json['next_period']) : null,
      cyclePhases: json['cycle_phases'] != null ? OvulationcalculatorDataCyclePhases.fromJson(json['cycle_phases']) : null,
      currentStatus: json['current_status'] != null ? OvulationcalculatorDataCurrentStatus.fromJson(json['current_status']) : null,
      disclaimer: json['disclaimer'],
    );
}

class OvulationcalculatorDataOvulation {
  String? date;
  num? daysFromLastPeriod;

  OvulationcalculatorDataOvulation({
    this.date,
    this.daysFromLastPeriod,
  });

  factory OvulationcalculatorDataOvulation.fromJson(Map<String, dynamic> json) => OvulationcalculatorDataOvulation(
      date: json['date'],
      daysFromLastPeriod: json['days_from_last_period'],
    );
}

class OvulationcalculatorDataFertileWindow {
  String? start;
  String? end;
  num? durationDays;

  OvulationcalculatorDataFertileWindow({
    this.start,
    this.end,
    this.durationDays,
  });

  factory OvulationcalculatorDataFertileWindow.fromJson(Map<String, dynamic> json) => OvulationcalculatorDataFertileWindow(
      start: json['start'],
      end: json['end'],
      durationDays: json['duration_days'],
    );
}

class OvulationcalculatorDataMostFertileDays {
  String? start;
  String? end;
  num? durationDays;

  OvulationcalculatorDataMostFertileDays({
    this.start,
    this.end,
    this.durationDays,
  });

  factory OvulationcalculatorDataMostFertileDays.fromJson(Map<String, dynamic> json) => OvulationcalculatorDataMostFertileDays(
      start: json['start'],
      end: json['end'],
      durationDays: json['duration_days'],
    );
}

class OvulationcalculatorDataFertileDaysItem {
  String? date;
  num? dayRelativeToOvulation;
  String? fertilityLevel;
  String? description;

  OvulationcalculatorDataFertileDaysItem({
    this.date,
    this.dayRelativeToOvulation,
    this.fertilityLevel,
    this.description,
  });

  factory OvulationcalculatorDataFertileDaysItem.fromJson(Map<String, dynamic> json) => OvulationcalculatorDataFertileDaysItem(
      date: json['date'],
      dayRelativeToOvulation: json['day_relative_to_ovulation'],
      fertilityLevel: json['fertility_level'],
      description: json['description'],
    );
}

class OvulationcalculatorDataNextPeriod {
  String? date;
  num? daysFromLastPeriod;

  OvulationcalculatorDataNextPeriod({
    this.date,
    this.daysFromLastPeriod,
  });

  factory OvulationcalculatorDataNextPeriod.fromJson(Map<String, dynamic> json) => OvulationcalculatorDataNextPeriod(
      date: json['date'],
      daysFromLastPeriod: json['days_from_last_period'],
    );
}

class OvulationcalculatorDataCyclePhases {
  OvulationcalculatorDataCyclePhasesFollicularPhase? follicularPhase;
  OvulationcalculatorDataCyclePhasesOvulation? ovulation;
  OvulationcalculatorDataCyclePhasesLutealPhase? lutealPhase;

  OvulationcalculatorDataCyclePhases({
    this.follicularPhase,
    this.ovulation,
    this.lutealPhase,
  });

  factory OvulationcalculatorDataCyclePhases.fromJson(Map<String, dynamic> json) => OvulationcalculatorDataCyclePhases(
      follicularPhase: json['follicular_phase'] != null ? OvulationcalculatorDataCyclePhasesFollicularPhase.fromJson(json['follicular_phase']) : null,
      ovulation: json['ovulation'] != null ? OvulationcalculatorDataCyclePhasesOvulation.fromJson(json['ovulation']) : null,
      lutealPhase: json['luteal_phase'] != null ? OvulationcalculatorDataCyclePhasesLutealPhase.fromJson(json['luteal_phase']) : null,
    );
}

class OvulationcalculatorDataCyclePhasesFollicularPhase {
  num? durationDays;
  String? description;

  OvulationcalculatorDataCyclePhasesFollicularPhase({
    this.durationDays,
    this.description,
  });

  factory OvulationcalculatorDataCyclePhasesFollicularPhase.fromJson(Map<String, dynamic> json) => OvulationcalculatorDataCyclePhasesFollicularPhase(
      durationDays: json['duration_days'],
      description: json['description'],
    );
}

class OvulationcalculatorDataCyclePhasesOvulation {
  num? durationDays;
  String? description;

  OvulationcalculatorDataCyclePhasesOvulation({
    this.durationDays,
    this.description,
  });

  factory OvulationcalculatorDataCyclePhasesOvulation.fromJson(Map<String, dynamic> json) => OvulationcalculatorDataCyclePhasesOvulation(
      durationDays: json['duration_days'],
      description: json['description'],
    );
}

class OvulationcalculatorDataCyclePhasesLutealPhase {
  num? durationDays;
  String? description;

  OvulationcalculatorDataCyclePhasesLutealPhase({
    this.durationDays,
    this.description,
  });

  factory OvulationcalculatorDataCyclePhasesLutealPhase.fromJson(Map<String, dynamic> json) => OvulationcalculatorDataCyclePhasesLutealPhase(
      durationDays: json['duration_days'],
      description: json['description'],
    );
}

class OvulationcalculatorDataCurrentStatus {
  String? currentPhase;
  bool? isFertile;
  num? daysUntilOvulation;
  num? daysUntilNextPeriod;

  OvulationcalculatorDataCurrentStatus({
    this.currentPhase,
    this.isFertile,
    this.daysUntilOvulation,
    this.daysUntilNextPeriod,
  });

  factory OvulationcalculatorDataCurrentStatus.fromJson(Map<String, dynamic> json) => OvulationcalculatorDataCurrentStatus(
      currentPhase: json['current_phase'],
      isFertile: json['is_fertile'],
      daysUntilOvulation: json['days_until_ovulation'],
      daysUntilNextPeriod: json['days_until_next_period'],
    );
}

class OvulationcalculatorRequest {
  String lastPeriod;
  int? cycleLength;

  OvulationcalculatorRequest({
    required this.lastPeriod,
    this.cycleLength,
  });

  Map<String, dynamic> toJson() => {
      'last_period': lastPeriod,
      if (cycleLength != null) 'cycle_length': cycleLength,
    };
}
