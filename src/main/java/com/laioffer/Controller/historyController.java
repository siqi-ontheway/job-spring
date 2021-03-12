package com.laioffer.Controller;
import com.laioffer.entity.Item;
import com.laioffer.handler.ResultResponse;
import com.laioffer.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class historyController {

    @Autowired
    private HistoryService historyService;
    @ResponseBody
    @RequestMapping(value = "/history/{user_id}", method = RequestMethod.GET)
    public Set<Item> getfavoriteditems(@PathVariable(value = "user_id") String userId) {
        Set<Item> itemlist = historyService.getFavoriteItems(userId);
        return itemlist;
    }
    @ResponseBody
    @RequestMapping(value = "/history", method = RequestMethod.POST)
    public ResultResponse addFavorite(@RequestBody Item item, @RequestBody String userId) {
        historyService.setFavoriteItems(userId,item);
        ResultResponse resultResponse = new ResultResponse("SUCCESS");
        return resultResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResultResponse unsetFavorite(@RequestBody Item favorite, @RequestBody String userId) {
        historyService.unsetFavoriteItems(userId,favorite.getId());
        ResultResponse resultResponse = new ResultResponse("SUCCESS");
        return resultResponse;
    }
}

