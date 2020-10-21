package kurlyk.transfer;

import kurlyk.common.Duet;
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
    // ID заданий, ID вопроса
    private List<Duet<Long, Long>> testQuestionIds;
    private List<Duet<Long, Long>> workQuestionIds;

    public void deleteTestQuestionId(Long id) {
        testQuestionIds.removeIf(o-> o.getB().equals(id));
    }

    public void deleteWorkQuestionId(Long id) {
        workQuestionIds.removeIf(o-> o.getB().equals(id));
    }

    public static QuestionIdsDto from(List<Question> questions){
        Map<Boolean, List<Question>> separatedQuestions = questions
                .stream()
                .collect(Collectors.partitioningBy(question -> question.getTask().getNumber() == 0));

        //Тестовые вопросы
        List<Duet<Long, Long>> testQuestionIds = separatedQuestions.get(true)
                .stream()
                .map(question -> new Duet<>(question.getTask().getId(), question.getId()))
                .collect(Collectors.toList());
        Collections.shuffle(testQuestionIds);

        //Лабораторные вопросы
        List<Duet<Long, Long>> workQuestionIds = separatedQuestions.get(false)
                .stream()
                .sorted(Comparator.comparing(Question::getNumber))
                .map(question -> new Duet<>(question.getTask().getId(), question.getId()))
                .collect(Collectors.toList());

        return QuestionIdsDto
                .builder()
                .testQuestionIds(testQuestionIds)
                .workQuestionIds(workQuestionIds)
                .build();
    }
}
