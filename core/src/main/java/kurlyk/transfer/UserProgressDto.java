package kurlyk.transfer;


import kurlyk.models.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProgressDto {

    private Long userId;
    private Long labWorkId;
    private Long taskId;
    private Long questionId;
    private Double score;

    public UserProgressDto(Integer userId) {
    }

    public UserProgress toUserProgress(){
        return UserProgress.builder()
                .id(null)
                .labWork(LabWork.builder().id(labWorkId).build())
                .task(Task.builder().id(taskId).build())
                .question(Question.builder().id(questionId).build())
                .user(User.builder().id(userId).build())
                .score(score)
                .build();
    }

    public static UserProgressDto fromUserProgress(UserProgress userProgress){
        return UserProgressDto.builder()
                .labWorkId(userProgress.getLabWork().getId())
                .taskId(userProgress.getTask().getId())
                .questionId(userProgress.getQuestion().getId())
                .userId(userProgress.getUser().getId())
                .score(userProgress.getScore())
                .build();
    }
}
