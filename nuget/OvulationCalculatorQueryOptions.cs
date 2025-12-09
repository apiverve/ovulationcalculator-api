using System;
using System.Collections.Generic;
using System.Text;
using Newtonsoft.Json;

namespace APIVerve.API.OvulationCalculator
{
    /// <summary>
    /// Query options for the Ovulation Calculator API
    /// </summary>
    public class OvulationCalculatorQueryOptions
    {
        /// <summary>
        /// First day of last menstrual period (YYYY-MM-DD)
        /// Example: 2024-01-01
        /// </summary>
        [JsonProperty("last_period")]
        public string Last_period { get; set; }

        /// <summary>
        /// Average menstrual cycle length in days (21-35)
        /// Example: 28
        /// </summary>
        [JsonProperty("cycle_length")]
        public string Cycle_length { get; set; }
    }
}
