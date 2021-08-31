package hogwartshouses.controller;

import hogwartshouses.model.DTO.RoomDTO;
import hogwartshouses.model.entity.RoomEntity;
import hogwartshouses.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("rooms", roomService.findAll());

        return "list-all-rooms";
    }

    @GetMapping(value = "/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        RoomDTO room = roomService.findRoomById(id);
        if (room == null) return "error";

        model.addAttribute("room", room);
        return "room";
    }

    @PostMapping
    public String save(@RequestBody RoomEntity roomEntity) {
        roomEntity.setId(null);
        roomService.saveRoom(roomEntity);

        return "redirect:/rooms";
    }

    @PutMapping("/{id}")
    public String update(@RequestBody RoomEntity roomEntity, @PathVariable Long id) {
        roomEntity.setId(id);
        roomService.saveRoom(roomEntity);

        return "redirect:/rooms";
    }

    @DeleteMapping(value = "/{id}")
    public String deleteById(@PathVariable(value = "id") Long id) {
        roomService.deleteRoomById(id);

        return "redirect:/rooms";
    }

    @GetMapping("/available")
    public String findAvailableRooms(Model model) {
        model.addAttribute("rooms", roomService.findAvailableRooms());

        return "list-all-rooms";
    }

    @GetMapping("/student/{id}")
    public String findAllResidentOfARoomByRoomId(@PathVariable(value = "id") Long studentId, Model model) {
        model.addAttribute("students", roomService.findAllResidentOfARoomByRoomId(studentId));

        return "students";
    }

    @GetMapping("/rat-owners")
    public String findAvailableRoomsForRatOwner(Model model) {
        model.addAttribute("rooms", roomService.findRoomWithNoCatOrOwl());

        return "list-all-rooms";
    }
}
