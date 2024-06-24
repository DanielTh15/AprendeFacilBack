package com.example.AprendeFacilBack.Domain.services;

import com.example.AprendeFacilBack.Domain.dto.Curso;
import com.example.AprendeFacilBack.Persistence.dao.CursoDAO;
import com.example.AprendeFacilBack.Web.Error.AprendoFacilCustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class CursoServiceImp implements CursoService{

    private final CursoDAO cursoDAO;

    private final AmazonS3ServiceInter amazonS3ServiceInter;

    @Autowired
    public CursoServiceImp(CursoDAO cursoDAO, AmazonS3ServiceInter amazonS3ServiceInter) {
        this.cursoDAO = cursoDAO;
        this.amazonS3ServiceInter = amazonS3ServiceInter;
    }

    @Override
    public List<Curso> getAll() {
        List<Curso> cursos = cursoDAO.getAll();
        for (Curso cur: cursos){
            String keyImage = cur.getImage();
            String urlImage = amazonS3ServiceInter.getDocUrl(keyImage);
            cur.setUrlImg(urlImage);
        }
        return cursos;
    }

    @Override
    public Curso save(Curso curso, MultipartFile image) {
        String keyImage = amazonS3ServiceInter.putFResourcePDFToTopic(image);
        curso.setImage(keyImage);
        return cursoDAO.save(curso);
    }

    @Override
    public List<Curso> getAllNameFromCurso(String q){
        List<Curso> cursosByName = cursoDAO.getAll();
        List<Curso> cursosSearching = new ArrayList<>();
        for (Curso cur: cursosByName){
            if (cur.getName().toLowerCase().contains(q.toLowerCase())){
                cursosSearching.add(cur);
            }
        }
        return cursosSearching;
    }

    @Override
    public Curso getCursoById(Integer id) throws AprendoFacilCustomException {
        Curso curso = cursoDAO.getCursoById(id);
        if (curso == null){
            throw new AprendoFacilCustomException("This course is not found", HttpStatus.NOT_FOUND);
        }
        return curso;
    }

}
