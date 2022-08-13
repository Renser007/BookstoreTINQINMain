package com.tinqin.api.model.bookbygenre;

import lombok.*;

@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class BookModel {
    private String bookName;
    private String series;
    private String author;
    private String price;
}
