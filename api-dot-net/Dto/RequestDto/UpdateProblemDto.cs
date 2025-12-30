using System.ComponentModel.DataAnnotations;
using System.Text.Json.Serialization;
using CjsApi.Dto;
using CjsApi.Models;

namespace CjsApi.Dto
{
    public class UpdateProblemDto
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
        public int TimeLimitMs { get; set; }

        [Range(64, 1024)]
        public int MemoryLimitMb { get; set; }

        public bool IsPublished { get; set; }

        public List<string> Tags { get; set; } = new();

        public List<TestCaseDto> TestCases { get; set; } = new();
    }
}
