declare module '@apiverve/ovulationcalculator' {
  export interface ovulationcalculatorOptions {
    api_key: string;
    secure?: boolean;
  }

  /**
   * Describes fields the current plan does not unlock. Locked fields arrive as null
   * in `data`; `locked_fields` names them, using dot paths for nested fields.
   * Absent when the plan unlocks everything.
   */
  export interface PremiumInfo {
    message: string;
    upgrade_url: string;
    locked_fields: string[];
  }

  export interface ovulationcalculatorResponse {
    status: string;
    error: string | null;
    data: OvulationCalculatorData;
    code?: number;
    premium?: PremiumInfo;
  }


  interface OvulationCalculatorData {
      lastPeriod:      Date | null;
      cycleLength:     number | null;
      ovulation:       NextPeriod;
      fertileWindow:   FertileWindow;
      mostFertileDays: FertileWindow;
      fertileDays:     FertileDay[];
      nextPeriod:      NextPeriod;
      cyclePhases:     CyclePhases;
      currentStatus:   CurrentStatus;
      disclaimer:      null | string;
  }
  
  interface CurrentStatus {
      currentPhase:        null | string;
      isFertile:           boolean | null;
      daysUntilOvulation:  number | null;
      daysUntilNextPeriod: number | null;
  }
  
  interface CyclePhases {
      follicularPhase: FollicularPhase;
      ovulation:       FollicularPhase;
      lutealPhase:     FollicularPhase;
  }
  
  interface FollicularPhase {
      durationDays: number | null;
      description:  null | string;
  }
  
  interface FertileDay {
      date:                   Date | null;
      dayRelativeToOvulation: number | null;
      fertilityLevel:         null | string;
      description:            null | string;
  }
  
  interface FertileWindow {
      start:        Date | null;
      end:          Date | null;
      durationDays: number | null;
  }
  
  interface NextPeriod {
      date:               Date | null;
      daysFromLastPeriod: number | null;
  }

  export default class ovulationcalculatorWrapper {
    constructor(options: ovulationcalculatorOptions);

    execute(callback: (error: any, data: ovulationcalculatorResponse | null) => void): Promise<ovulationcalculatorResponse>;
    execute(query: Record<string, any>, callback: (error: any, data: ovulationcalculatorResponse | null) => void): Promise<ovulationcalculatorResponse>;
    execute(query?: Record<string, any>): Promise<ovulationcalculatorResponse>;
  }
}
