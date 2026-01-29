package edu.esiea.auth_service.dto;



import java.util.List;


public record PageDto<T>(List<T> data, int pageNumber, int totalPages) {
}
