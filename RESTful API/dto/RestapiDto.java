public class RestapiDto {

    public record RestapiCreateRequest(
        @NotBlank(message = "제목은 필수입니다.")
        @Size(max = 100)
        String title,

        @NotBlank(message = "내용은 필수입니다.")
        String content,

        @Email(message = "이메일 형식이 올바르지 않습니다.")
        String authorEmail
    ) {
        // 간단한 로직, 정적 팩토리 메서드
    }

    // 게시글 작성 요청
    public record CreateRequest(
        String title,
        String content
    ) {}

    // 게시글 수정 요청
    public record UpdateRequest(
        String title,
        String content
    ) {}

    // 게시글 응답 (목록용)
    public record SimpleResponse(
        Long id,
        String title
    ) {}
}