package kurlyk.transfer;

import kurlyk.model.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionIdsDto {

    private List<Long> testQuestionIds;
    private List<Long> workQuestionIds;

    public static QuestionIdsDto from(List<Question> questions){
        Map<Boolean, List<Question>> separatedQuestions = questions
                .stream()
                .collect(Collectors.partitioningBy(question -> question.getTask().getNumber() == 0));

        //Тестовые вопросы
        List<Long> testQuestionIds = separatedQuestions.get(true)
                .stream()
                .map(Question::getId)
                .collect(Collectors.toList());
        Collections.shuffle(testQuestionIds);

        //Лабораторные вопросы
        List<Long> workQuestionIds = separatedQuestions.get(false)
                .stream()
                .sorted(Comparator.comparing(Question::getNumber))
                .map(Question::getId)
                .collect(Collectors.toList());

        return QuestionIdsDto
                .builder()
                .testQuestionIds(testQuestionIds)
                .workQuestionIds(workQuestionIds)
                .build();
    }
}
