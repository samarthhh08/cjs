using System.ComponentModel.DataAnnotations;
using System.Text.Json.Serialization;
using CjsApi.Models;

namespace CjsApi.Dto
{
    public class CreateProblemDto
    {
        [Required]
        [MaxLength(150)]
        public string Title { get; set; } = null!;

        [Required]
        public string Description { get; set; } = null!;

        [Required]
        [JsonConverter(typeof(JsonStringEnumConverter))]
        public Difficulty Difficulty { get; set; }

        [Range(100, 10000)]
        public int TimeLimitMs { get; set; } = 1000;

        [Range(64, 1024)]
        public int MemoryLimitMb { get; set; } = 1024;

        public bool IsPublished { get; set; } = true;

        // Tags sent as simple strings
        public List<string> Tags { get; set; } = new();

        // Test cases
        public List<TestCaseDto> TestCases { get; set; } = new();
    }
}
