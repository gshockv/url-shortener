package io.gshockv.shrtr.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkPage<T> {
  public static final int PAGE_SIZE = 11;

   private List<T> items;
   private int page;
   private int pagesCount;

   public static <T> ShortLinkPage<T> of(List<T> items, int pageNumber, int totalPages) {
     return new ShortLinkPage<>(items, pageNumber, totalPages);
   }

   public static <T> ShortLinkPage<T> empty() {
     return of(List.of(), 0, 0);
   }
}
