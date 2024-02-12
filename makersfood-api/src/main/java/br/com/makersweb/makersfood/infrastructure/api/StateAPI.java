package br.com.makersweb.makersfood.infrastructure.api;

import br.com.makersweb.makersfood.domain.pagination.Pagination;
import br.com.makersweb.makersfood.infrastructure.state.models.CreateStateRequest;
import br.com.makersweb.makersfood.infrastructure.state.models.StateListResponse;
import br.com.makersweb.makersfood.infrastructure.state.models.StateResponse;
import br.com.makersweb.makersfood.infrastructure.state.models.UpdateStateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author aaristides
 */
@RequestMapping(value = "states")
@Tag(name = "States")
public interface StateAPI {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create a new state")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> createState(@RequestBody CreateStateRequest input);

    @GetMapping
    @Operation(summary = "List all states paginated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listed successfully"),
            @ApiResponse(responseCode = "422", description = "A invalid parameter was received"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    Pagination<StateListResponse> listStates(
            @RequestParam(name = "search", required = false, defaultValue = "") final String search,
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "name") final String sort,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") final String direction
    );

    @GetMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Get a state by it's identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "State retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "State was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    StateResponse getById(@PathVariable(name = "id") String id);

    @PutMapping(
            value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Update a state by it's identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "State updated successfully"),
            @ApiResponse(responseCode = "404", description = "State was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> updateById(@PathVariable(name = "id") String id, @RequestBody UpdateStateRequest input);

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a state by it's identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "State deleted successfully"),
            @ApiResponse(responseCode = "404", description = "State was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    void deleteById(@PathVariable(name = "id") String id);

}
