declare module '@apiverve/ovulationcalculator' {
  export interface ovulationcalculatorOptions {
    api_key: string;
    secure?: boolean;
  }

  export interface ovulationcalculatorResponse {
    status: string;
    error: string | null;
    data: OvulationCalculatorData;
    code?: number;
  }


  interface OvulationCalculatorData {
      lastPeriod:      Date;
      cycleLength:     number;
      ovulation:       NextPeriod;
      fertileWindow:   FertileWindow;
      mostFertileDays: FertileWindow;
      fertileDays:     FertileDay[];
      nextPeriod:      NextPeriod;
      cyclePhases:     CyclePhases;
      currentStatus:   CurrentStatus;
      disclaimer:      string;
  }
  
  interface CurrentStatus {
      currentPhase:        string;
      isFertile:           boolean;
      daysUntilOvulation:  number;
      daysUntilNextPeriod: number;
  }
  
  interface CyclePhases {
      follicularPhase: FollicularPhase;
      ovulation:       FollicularPhase;
      lutealPhase:     FollicularPhase;
  }
  
  interface FollicularPhase {
      durationDays: number;
      description:  string;
  }
  
  interface FertileDay {
      date:                   Date;
      dayRelativeToOvulation: number;
      fertilityLevel:         string;
      description:            string;
  }
  
  interface FertileWindow {
      start:        Date;
      end:          Date;
      durationDays: number;
  }
  
  interface NextPeriod {
      date:               Date;
      daysFromLastPeriod: number;
  }

  export default class ovulationcalculatorWrapper {
    constructor(options: ovulationcalculatorOptions);

    execute(callback: (error: any, data: ovulationcalculatorResponse | null) => void): Promise<ovulationcalculatorResponse>;
    execute(query: Record<string, any>, callback: (error: any, data: ovulationcalculatorResponse | null) => void): Promise<ovulationcalculatorResponse>;
    execute(query?: Record<string, any>): Promise<ovulationcalculatorResponse>;
  }
}
