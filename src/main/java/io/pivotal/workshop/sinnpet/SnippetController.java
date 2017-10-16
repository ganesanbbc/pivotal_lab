package io.pivotal.workshop.sinnpet;

import io.pivotal.workshop.sinnpet.db.SnippetDBBasedRepository;
import io.pivotal.workshop.sinnpet.db.SnippetListBasedRepository;
import io.pivotal.workshop.sinnpet.model.NewSnippetFields;
import io.pivotal.workshop.sinnpet.model.SnippetInfo;
import io.pivotal.workshop.sinnpet.model.SnippetRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/snippets")
public class SnippetController {

    @Autowired
    private SnippetDBBasedRepository snippetRepository;

    @Autowired
    private SnippetPresenter snippetPresenter;


    @GetMapping
    public List<SnippetInfo> snippets() {
        return snippetRepository.findAll()
                .stream()
                .map(snippetPresenter::present)
                .collect(toList());
    }

    @GetMapping("/{id}")
    public SnippetInfo snippet(@PathVariable("id") String id) {
        SnippetRecord record = snippetRepository.findOne(id);
        return snippetPresenter.present(record);
    }

    @PostMapping
    public ResponseEntity<SnippetInfo> add(@RequestBody NewSnippetFields newSnippetFields) {
        SnippetRecord savedSnippetRecord = snippetRepository.save(newSnippetFields);
        SnippetInfo savedSnippetInfo = snippetPresenter.present(savedSnippetRecord);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(buildSnippetUri(savedSnippetInfo));

        return new ResponseEntity<>(savedSnippetInfo, httpHeaders, HttpStatus.CREATED);
    }


    private URI buildSnippetUri(SnippetInfo snippet) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest().path("/" + snippet.id)
                .buildAndExpand().toUri();
    }
}