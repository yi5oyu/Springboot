import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:9000")
public class ReactController {

    private final RestapiService restapiService;

     /**
     * 전체 목록 조회
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<PostResponse>>> getAllPosts() {
        List<PostResponse> posts = postService.findAll();
        return ResponseEntity.ok(ApiResponse.success(posts));
    }

    /**
     * 단건 상세 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponse>> getPost(@PathVariable Long id) {
        PostResponse post = postService.findById(id);
        return ResponseEntity.ok(ApiResponse.success(post));
    }

    /**
     * 데이터 생성 (201 Created 반환)
     */
    @PostMapping
    public ResponseEntity<ApiResponse<PostResponse>> createPost(@Valid @RequestBody PostRequest request) {
        PostResponse savedPost = postService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success("Post created successfully", savedPost));
    }

    @PutMapping


    /**
     * 데이터 수정 (PATCH: 부분 수정 추천)
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponse>> updatePost(
        @PathVariable Long id,
        @Valid @RequestBody PostUpdateRequest request) {
        PostResponse updatedPost = postService.update(id, request);
        return ResponseEntity.ok(ApiResponse.success("Post updated successfully", updatedPost));
    }

    /**
     * 데이터 삭제 (204 No Content 반환)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
