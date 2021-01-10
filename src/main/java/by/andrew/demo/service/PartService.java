package by.andrew.demo.service;

import by.andrew.demo.dto.CarDTO;
import by.andrew.demo.dto.CategoryDTO;
import by.andrew.demo.entity.parts.Part;
import by.andrew.demo.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartService {
    @Autowired
    private PartRepository partRepository;

    public void add(Part part){
        partRepository.save(part);
    }

    public List<Part> getAll() {
        return partRepository.findAll();
    }

    public List<Part> getBySort(CarDTO carDTO, CategoryDTO categoryDTO, String name) {
        List<Part> all = partRepository.findAll();
        List<Part> allByCar = new ArrayList<>();
        List<Part> allByCategory = new ArrayList<>();
        List<Part> allBySort = new ArrayList<>();
        for (Part byCar : all) {
            if (byCar.getCar().getBrand().equals(carDTO.getBrand()) ||
                    byCar.getCar().getModel().equals(carDTO.getModel()) ||
                    byCar.getCar().getYear() == carDTO.getYear()) {
                allByCar.add(byCar);
            }
        }
        for (Part byCategory : allByCar) {
            if (byCategory.getCategory().getCategoryName().equals(categoryDTO.getCategoryName())){
                allByCategory.add(byCategory);
            }
        }
        for (Part bySort:allByCategory) {
            if (bySort.getName().equals(name)){
                allBySort.add(bySort);
            }
        }
        return allBySort;
    }
}
