package com.github.valentina810.diaryofemotions.controllers;

import com.github.valentina810.diaryofemotions.domain.dto.EmotionCreateDto;
import com.github.valentina810.diaryofemotions.domain.dto.EmotionDto;
import com.github.valentina810.diaryofemotions.services.EmotionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/emotions")
@Validated
@Tag(name = "Управление справочником Эмоции")
public class AdminEmotionController {

    private final EmotionService emotionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Добавление новой эмоции")
    public EmotionDto createEmotion(@RequestBody EmotionCreateDto emotionCreateDto) {
        return emotionService.saveEmotion(emotionCreateDto);
    }

    @DeleteMapping("/{emotionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Удаление эмоции по id")
    public void createEmotion(@PathVariable @Parameter(description = "Идентификатор эмоции") long emotionId) {
        emotionService.deleteEmotion(emotionId);
    }

    @PatchMapping("/{emotionId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Редактирование эмоции")
    public EmotionDto updateEmotion(@PathVariable @Parameter(description = "Идентификатор эмоции") long emotionId,
                                    @Valid @RequestBody EmotionCreateDto emotionCreateDto) {
        return emotionService.updateEmotion(emotionId, emotionCreateDto);
    }
}