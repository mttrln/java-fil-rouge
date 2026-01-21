package edu.esiea.tp_sb.dto;



import java.util.List;


public record PageDto<T>(List<T> data, int pageNumber, int totalPages) {
}
