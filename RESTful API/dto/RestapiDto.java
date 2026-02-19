import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

// @formatter:off

/**
 * REST API 데이터 전송을 위한 표준 DTO 모듈
 */
public class RestapiDto {

    /**
     * POST: 리소스 생성 요청
     */
    public record CreateRequest(
        @NotBlank(message = "제목은 비어 있을 수 없습니다.")
        @Size(max = 100, message = "제목은 100자 이내여야 합니다.")
        String title,
        
        @NotBlank(message = "내용은 필수 입력 사항입니다.")
        String content,

        @NotNull(message = "카테고리 ID는 필수입니다.")
        Long categoryId
    ) {}

    /**
     * PATCH: 리소스 수정 요청
     */
    public record UpdateRequest(
        @Size(max = 100, message = "제목은 100자 이내여야 합니다.")
        String title,
        
        String content
    ) {}

    /**
     * GET: 리소스 상세 응답
     */
    public record Response(
        Long id,
        String title,
        String content,
        String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {}

    /**
     * GET: 리소스 목록 응답
     */
    public record SummaryResponse(
        Long id,
        String title,
        String name,
        LocalDateTime createdAt
    ) {}
}