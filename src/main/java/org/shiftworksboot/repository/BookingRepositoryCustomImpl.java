package org.shiftworksboot.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.shiftworksboot.dto.BookingSearchDto;
import org.shiftworksboot.entity.Booking;
import org.shiftworksboot.entity.QBooking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class BookingRepositoryCustomImpl implements BookingRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public BookingRepositoryCustomImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    private BooleanExpression searchByLike(String searchBy, String searchQuery){
        if(StringUtils.pathEquals("bookTitle", searchBy)){
            return QBooking.booking.bookTitle.like("%"+searchQuery+"%");
        } else if (StringUtils.pathEquals("bookContent", searchBy)) {
            return QBooking.booking.bookContent.like("%"+searchQuery+"%");
        }

        return null;
    }

    @Override
    public Page<Booking> getBookingPage(BookingSearchDto bookingSearchDto, Pageable pageable) {
        QueryResults<Booking> results = queryFactory
                .selectFrom(QBooking.booking)
                .where(searchByLike(bookingSearchDto.getSearchBy(),
                        bookingSearchDto.getSearchQuery()))
                .orderBy(QBooking.booking.bookId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Booking> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }
}
